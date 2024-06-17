import React, {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {
    Badge,
    Button,
    Center,
    Flex,
    Heading,
    Stack,
    Text,
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalFooter,
    ModalBody,
    ModalCloseButton,
    useColorModeValue, FormLabel, Input, FormControl, useDisclosure,
} from '@chakra-ui/react'
import SideBar from "../communal/SideBar";
import CommentService from "../../service/CommentService";
import {useUser} from "../../service/UserContext";

const PostDetail = () => {
    const [commentsData, setCommentsData] = useState(null);
    const [commentTitle, setCommentTitle] = useState("");
    const [commentContent, setCommentContent] = useState("");
    const [loading, setLoading] = useState(true);
    const location = useLocation();
    const navigate = useNavigate();
    const { user } = useUser();
    const {postId, author, title, description, category, comments} = location.state || {};
    const [curComments, setCurComments] = useState(comments);
    const { isOpen, onOpen, onClose } = useDisclosure();
    const initialRef = React.useRef(null)

    const getCommentsData = async () => {
        setLoading(true);
        try {
            // console.log("the commentId is: " + comments)
            if (curComments) {
                curComments.map(id => console.log(id))
                const tempComments = await Promise.all(curComments.map(id => CommentService.getCommentById(id)));
                setCommentsData(tempComments);
                // console.log("the all comments: " + JSON.stringify(commentsData));
            } else console.log("the comments is empty")
        } catch (error) {
            console.error('Exist Error when fetching comments: ' + error);
        } finally {
            setLoading(false)
        }
    }
    
    const handleAddComment = async () => {
        if (!user) throw "user not exist";
        console.log("want to add a comment, the current user is: " + JSON.stringify(user));
        console.log("Add comment, the title is: " + commentTitle + " the content is: " + commentContent);
        console.log("current comments sets before add new: " + JSON.stringify(comments))
        const reviewer = user.email;
        const newComment = await CommentService.postComment(reviewer, commentTitle, commentContent, postId);
        console.log("the new comment content is: " + JSON.stringify(newComment));
        await setCurComments([...curComments, newComment.commentId]);
        console.log("after add new Comment: " + JSON.stringify(curComments))
        // navigate("/")

    }

    useEffect(() => {
        getCommentsData();
        console.log("in PostDetail, the commentsData is: " + JSON.stringify(commentsData));
        onClose();
    }, [comments, curComments])

    if (loading) return <div>Loading...</div>

    return <div>
        <SideBar />
        <Center py={6} marginTop="5%">
        <Stack
            borderWidth="1px"
            borderRadius="lg"
            w={{ sm: '100%', md: '540px' }}
            height={{ sm: '476px', md: '20rem' }}
            direction={{ base: 'column', md: 'row' }}
            // eslint-disable-next-line react-hooks/rules-of-hooks
            bg={useColorModeValue('white', 'gray.900')}
            boxShadow={'2xl'}
            padding={4}>
            <Stack
                flex={1}
                flexDirection="column"
                justifyContent="center"
                alignItems="center"
                p={1}
                pt={2}>
                <Heading fontSize={'2xl'} fontFamily={'body'}>
                    {title}
                </Heading>
                <Text fontWeight={600} color={'gray.500'} size="sm" mb={4}>
                    {author}
                </Text>
                <Text
                    textAlign={'center'}
                    // eslint-disable-next-line react-hooks/rules-of-hooks
                    color={useColorModeValue('gray.700', 'gray.400')}
                    px={3}>
                    {description}
                </Text>
                <Stack align={'center'} justify={'center'} direction={'row'} mt={6}>
                    <Badge
                        px={2}
                        py={1}
                        // eslint-disable-next-line react-hooks/rules-of-hooks
                        bg={useColorModeValue('gray.50', 'gray.800')}
                        fontWeight={'400'}>
                        #{category}
                    </Badge>
                </Stack>

                <Stack
                    width={'100%'}
                    mt={'2rem'}
                    direction={'row'}
                    padding={2}
                    justifyContent={'space-between'}
                    alignItems={'center'}>
                    <Button
                        flex={1}
                        fontSize={'sm'}
                        rounded={'full'}
                        _focus={{
                            bg: 'gray.200',
                        }}
                        onClick={onOpen}
                    >
                        Comment
                    </Button>
                    <Modal
                        initialFocusRef={initialRef}
                        isOpen={isOpen}
                        onClose={onClose}
                    >
                        <ModalOverlay />
                        <ModalContent>
                            <ModalHeader>Create your account</ModalHeader>
                            <ModalCloseButton />
                            <ModalBody pb={6}>
                                <FormControl>
                                    <FormLabel>Author</FormLabel>
                                    <Input
                                        id={'reviewer'}
                                        name={'reviewer'}
                                        value={user?.email || ''}
                                        isReadOnly
                                    />
                                </FormControl>

                                <FormControl>
                                    <FormLabel>Title</FormLabel>
                                    <Input
                                        id={'title'}
                                        name={'title'}
                                        ref={initialRef}
                                        placeholder='Comment Title'
                                        onChange={(e) => setCommentTitle(e.target.value)}
                                    />
                                </FormControl>

                                <FormControl mt={4}>
                                    <FormLabel>Content</FormLabel>
                                    <Input
                                        id={'body'}
                                        name={'body'}
                                        placeholder='Comment content'
                                        onChange={(e) => setCommentContent(e.target.value)}
                                    />
                                </FormControl>
                            </ModalBody>

                            <ModalFooter>
                                <Button colorScheme='blue' mr={3} onClick={handleAddComment}>
                                    Save
                                </Button>
                                <Button onClick={onClose}>Cancel</Button>
                            </ModalFooter>
                        </ModalContent>
                    </Modal>
                    <Button
                        flex={1}
                        fontSize={'sm'}
                        rounded={'full'}
                        bg={'blue.400'}
                        color={'white'}
                        boxShadow={
                            '0px 1px 25px -5px rgb(66 153 225 / 48%), 0 10px 10px -5px rgb(66 153 225 / 43%)'
                        }
                        _hover={{
                            bg: 'blue.500',
                        }}
                        _focus={{
                            bg: 'blue.500',
                        }}>
                        Follow
                    </Button>
                </Stack>
            </Stack>
        </Stack>
    </Center>
        <Heading fontSize={'2xl'} fontFamily={'body'} align={'center'} justifyContent={'center'} marginTop={'3%'}>
            Comments
        </Heading>
        {commentsData ? (commentsData.map((comment, index) => (
                <Center py={4}>
                    <Stack
                        borderWidth="1px"
                        borderRadius="lg"
                        w="60%"
                        minH="8rem"
                        // height={{ sm: '476px', md: '20rem' }}
                        direction={{ base: 'column', md: 'row' }}
                        // eslint-disable-next-line react-hooks/rules-of-hooks
                        bg={useColorModeValue('white', 'gray.900')}
                        boxShadow={'2xl'}
                        padding={4}
                        // align="center"
                        // justify="center"
                        mx="auto"
                    >
                        <Stack
                            flex={2}
                            flexDirection="column"
                            // justifyContent="center"
                            // alignItems="center"
                            p={1}
                            pt={1}
                        >
                            <Flex width={'100%'} justifyContent={'space-between'}>
                                <Heading fontSize={'1xl'} fontFamily={'body'}>
                                    Title: {comment.title}
                                </Heading>
                                <Text fontWeight={500} color={'gray.500'} size="sm" >
                                    {comment.reviewer}
                                </Text>
                            </Flex>

                            <Text
                                // textAlign={'center'}
                                // eslint-disable-next-line react-hooks/rules-of-hooks
                                color={useColorModeValue('gray.700', 'gray.400')}
                                px={2}>
                                {comment.body}
                            </Text>
                        </Stack>
                    </Stack>
                </Center>))
        ): (<div>No Comment ~</div>
        )}
    </div>
}

export default PostDetail;